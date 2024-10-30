import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';
import {PLANT_LINK} from "@/constants";

const Footer: React.FC = () => {
  const defaultMessage="LightingForest出品"
  const currentYear=new Date().getFullYear()
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear}${defaultMessage}`}
      links={[
        {
          key: 'plant',
          title: '知识星球',
          href: PLANT_LINK,
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/LightingForest',
          blankTarget: true,
        },
        {
          key: 'codeNav',
          title: '编程导航',
          href: 'https://www.code-nav.cn/',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
